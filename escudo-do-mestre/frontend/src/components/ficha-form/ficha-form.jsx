import { useState } from "react";
import { atualizarFicha, createFicha } from "../../infra/ficha";
import "./ficha-form.css"

// Campos de texto simples da ficha
const CAMPOS_TEXTO = [
    { name: "jogador", label: "Jogador" },
    { name: "nome", label: "Nome do Personagem" },
    { name: "ancestralidade", label: "Ancestralidade" },
    { name: "aprendiz", label: "Aprendiz" },
    { name: "especialista", label: "Especialista" },
    { name: "profissao", label: "Profissão" },
];

// Atributos numéricos da ficha
const CAMPOS_NUMERO = [
    { name: "nivel", label: "Nível" },
    { name: "poder", label: "Poder" },
    { name: "intelecto", label: "Intelecto" },
    { name: "agilidade", label: "Agilidade" },
    { name: "forca", label: "Força" },
    { name: "saude", label: "Saúde" },
    { name: "vontade", label: "Vontade" },
    { name: "percepcao", label: "Percepção" },
    { name: "deslocamento", label: "Deslocamento" },
    { name: "tamanho", label: "Tamanho" },
    { name: "defesa", label: "Defesa" },
    { name: "corrupcao", label: "Corrupção" },
    { name: "insanidade", label: "Insanidade" },
];

// Estado inicial: todos os campos vazios / zerados
const FICHA_VAZIA = {
    jogador: "", nome: "", descricao: "", ancestralidade: "", aprendiz: "",
    especialista: "", profissao: "", nivel: 0, poder: 0, intelecto: 0,
    agilidade: 0, forca: 0, saude: 0, vontade: 0, percepcao: 0,
    deslocamento: 0, tamanho: 0, defesa: 0, corrupcao: 0, insanidade: 0,
};

function FichaForm({ onClose, onFichasAlteradas, mesaId, ficha }) {
    const [dados, setDados] = useState({ ...FICHA_VAZIA, ...ficha });

    const isEditando = Boolean(ficha);

    function handleChange(name, value, numerico) {
        setDados(prev => ({
            ...prev,
            [name]: numerico ? Number(value) : value,
        }));
    }

    async function handleSubmit(e) {
        e.preventDefault();

        if (isEditando) {
            await atualizarFicha(ficha.id, { ...dados, mesaId });
        } else {
            await createFicha(dados, mesaId);
        }

        await onFichasAlteradas();
        onClose();
    }

    return (
        <div className="ficha-form">
            <div className="title">
                <h2>{isEditando ? "Atualize" : "Crie"} a ficha</h2>
            </div>
            <form onSubmit={handleSubmit}>
                <div className="actions">
                    <button type="button" onClick={onClose}>X</button>
                </div>

                {CAMPOS_TEXTO.map(campo => (
                    <div className="input" key={campo.name}>
                        <label htmlFor={campo.name}>{campo.label}:</label>
                        <input
                            type="text"
                            id={campo.name}
                            name={campo.name}
                            value={dados[campo.name]}
                            onChange={(e) => handleChange(campo.name, e.target.value, false)}
                        />
                    </div>
                ))}

                <div className="input">
                    <label htmlFor="descricao">Descrição:</label>
                    <textarea
                        id="descricao"
                        name="descricao"
                        value={dados.descricao}
                        onChange={(e) => handleChange("descricao", e.target.value, false)}
                    ></textarea>
                </div>

                <div className="atributos">
                    {CAMPOS_NUMERO.map(campo => (
                        <div className="input" key={campo.name}>
                            <label htmlFor={campo.name}>{campo.label}:</label>
                            <input
                                type="number"
                                id={campo.name}
                                name={campo.name}
                                value={dados[campo.name]}
                                onChange={(e) => handleChange(campo.name, e.target.value, true)}
                            />
                        </div>
                    ))}
                </div>

                <div className="submit-button">
                    <button type="submit">{isEditando ? "Atualizar Ficha" : "Criar Ficha"}</button>
                </div>
            </form>
        </div>
    );
}

export default FichaForm;
