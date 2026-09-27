# Como rodar no Kubernetes

## 1. Instalar (só uma vez)

```bash
winget install Kubernetes.kubectl
winget install Kubernetes.kind
```

## 2. Subir o cluster

```bash
kind create cluster --name escudo-mestre
```

## 3. Buildar e carregar as imagens
Eureka Server:
```bash
docker build -t eureka-server:1.0 ./eureka-server
kind load docker-image eureka-server:1.0 --name escudo-mestre
```

Config Server:
```bash
docker build -t config-server:1.0 ./config-server
kind load docker-image config-server:1.0 --name escudo-mestre
```

Catálogo de mesas:
```bash
docker build -t catalogo-filmes-service:1.0 ./escudo-do-mestre
kind load docker-image escudo-do-mestre-service:1.0 --name escudo-mestre
```

Catálogo de fichas:
```bash
docker build -t ratings-catalog-service:1.0 ./ficha-microservice
kind load docker-image ficha-service:1.0 --name escudo-mestre
```

Gateway:
```bash
docker build -t gateway:1.0 ./gateway
kind load docker-image gateway:1.0 --name escudo-mestre
```

## 4. Aplicar os manifests

```bash
kubectl apply -f k8s/
```

## 5. Ver se subiu

```bash
kubectl get pods -n escudo-mestre -w
```

Espera até todos ficarem `1/1 Running` (Ctrl+C pra sair do watch).

## 6. Acessar

Os Services sao ClusterIP (so existem dentro do cluster). Pra acessar do computador,
abre um tunel:

```bash
kubectl port-forward -n escudo-mestre svc/gateway 8080:8080
```

Depois, tudo passa pelo gateway usando o nome do servico como prefixo:

```bash
curl http://localhost:8080/escudo-do-mestre/mesas/mestre/1
```

Pra ver o painel do Eureka: 
```bash
kubectl port-forward -n escudo-mestre svc/eureka-server 8761:8761
```

## Tres pegadinhas que quebram o acesso

Se os pods ficarem `0/1 Running` pra sempre, ou o gateway devolver 404/500,
provavelmente e um destes:

**1. config-server com backend git apontando pro ConfigMap.** O ConfigMap monta
os `.properties` como arquivos soltos, sem `.git`, e o backend git morre com
`No .git at file:///config-repo`. No k8s use o backend **native**
(`SPRING_PROFILES_ACTIVE=native` + `SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCH_LOCATIONS`).
Como o `spring.config.import` e `optional:`, a app sobe mesmo assim — so que sem
o `server.port`, indo pra 8080 padrao, e o readinessProbe em 8081/8082 nunca passa.

**2. `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE` nao funciona sozinho.** `serviceUrl` e
um `Map`, e a env var vira a chave minuscula `defaultzone`; o Eureka le `defaultZone`,
entao o default `localhost:8761` vence e o servico nunca se registra. Use
`SPRING_APPLICATION_JSON`, que preserva o case.

**3. Eureka registrando o hostname do Pod.** Pod nao tem registro no DNS do cluster,
so Service — o gateway falha com `UnknownHostException` / `NXDOMAIN`. Force o IP do
Pod com `EUREKA_INSTANCE_PREFER_IP_ADDRESS` + `EUREKA_INSTANCE_IP_ADDRESS` vindo da
downward API (`status.podIP`).

Comandos uteis pra diagnosticar:

```bash
kubectl get pods -n escudo-mestre                                    # quem esta 0/1
kubectl logs -n escudo-mestre deploy/escudo-do-mestre-service | grep Tomcat  # subiu em qual porta?
kubectl exec -n escudo-mestre deploy/config-server -- \
  wget -qO- http://localhost:8888/escudo-do-mestre-service/docker        # o config chega?
kubectl exec -n escudo-mestre deploy/eureka-server -- \
  wget -qO- http://localhost:8761/eureka/apps | grep homePageUrl # registrou por IP?
```

---

### Mudei o código de um serviço, e agora?

```bash
docker build -t catalogo-filmes-service:1.0 ./catalogo-filmes-service
kind load docker-image catalogo-filmes-service:1.0 --name escudo-mestre
kubectl rollout restart deployment/catalogo-filmes-service -n escudo-mestre
```

### Pra derrubar tudo

```bash
kind delete cluster --name escudo-mestre
```


