import { useEffect, useState } from 'react'
import './App.css'
import { getMesas } from './infra/mesa';
import { Header, MesaCard } from './components';

function App() {

  const [mesas, setMesas] = useState([]);

  async function carregarMesas() {
    const dados = await getMesas();
    setMesas(dados);
  }

  useEffect(() => {
    carregarMesas();
  }, []);

  return (
    <>
    <div>
        <Header onMesasCriadas={carregarMesas} />
    </div>
      <div>
        {mesas?.map(mesa => (
          <MesaCard key={mesa.id} mesa={mesa} onAlteradas={carregarMesas} />
        ))}
      </div>
    </>
  )
}

export default App
