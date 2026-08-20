import { useState } from "react";
import { deletarMesa } from "../../infra/mesa";
import "./mesa-card.css"
import MesaForm from "../mesa-form/mesa-form";

function MesaCard({ mesa, onAlteradas }) {

    const [modalVisivel, setModalVisivel] = useState(false);
    
    function handleAtualizarMesa() {
        setModalVisivel(prev => !prev);
    }   

    async function handleAtualizarMesa() {
        setModalVisivel(prev => !prev);
    }

    async function handleDeletarMesa() {
        await deletarMesa(mesa.id);
        alert("Mesa deletada com sucesso!");
        onAlteradas();
    }

    return (
        <div>
            <div className="mesa-card">
                <div>
                    <h2>{mesa.nome}</h2>
                    <p>{mesa.descricao}</p>
                    <p>Mesa de {mesa.mestre}</p>
                </div>
                <div>
                    <button>Exibir Fichas</button>
                    <button onClick={handleAtualizarMesa}>Atualizar</button>
                    <button onClick={handleDeletarMesa}>Deletar</button>
                </div>
            </div>
            {modalVisivel && (
                <MesaForm onClose={handleAtualizarMesa} onMesasCriadas={onAlteradas} mesa={mesa} />
            )}
        </div>
    );
}

export default MesaCard;