import { useState } from "react";
import { atualizarMesa, createMesa } from "../../infra/mesa";
import "./mesa-form.css"

function MesaForm({ onClose, onMesasCriadas, mesa }) {
    const [nome, setNome] = useState(mesa?.nome || "");
    const [descricao, setDescricao] = useState(mesa?.descricao || "");
    const [mestre, setMestre] = useState(mesa?.mestre || "");

    const isEditando = Boolean(mesa);

    async function handleSubmit(e) {
        e.preventDefault();

        const novaMesa = {
            ...mesa,
            nome,
            descricao,
            mestre
        };

        if (isEditando) {
            await atualizarMesa(novaMesa.id, novaMesa);
        } else {
            await createMesa(novaMesa);
        }

        await onMesasCriadas();

        setNome("");
        setDescricao("");
        setMestre("");
        
        onClose();
    }

    return (
        <div className="mesa-form">
            <div className="title">
                <h2>{isEditando ? "Atualize" : "Crie"} sua mesa</h2>
            </div>
            <form onSubmit={handleSubmit}>
                <div className="actions">
                    <button type="button" onClick={onClose}>X</button>
                </div>
                <div className="input">
                    <label htmlFor="nome">Nome da Mesa:</label>
                    <input type="text" id="nome" name="nome" required value={nome} onChange={(e) => setNome(e.target.value)} />
                </div>
                <div className="input">
                    <label htmlFor="mestre">Mestre da Mesa:</label>
                    <input type="text" id="mestre" name="mestre" required value={mestre} onChange={(e) => setMestre(e.target.value)} />
                </div>
                <div className="input">
                    <label htmlFor="descricao">Descrição:</label>
                    <textarea id="descricao" name="descricao" required value={descricao} onChange={(e) => setDescricao(e.target.value)} ></textarea>
                </div>
                <div className="submit-button">
                    <button type="submit">{isEditando ? "Atualizar Mesa" : "Criar Mesa"}</button>
                </div>
            </form>
        </div>
    );
}

export default MesaForm;