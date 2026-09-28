import { useState } from "react";
import { deletarMesa } from "../../infra/mesa";
import "./mesa-card.css"
import MesaForm from "../mesa-form/mesa-form";
import FichaList from "../ficha-list/ficha-list";

function MesaCard({ mesa, onAlteradas }) {

    const [modalVisivel, setModalVisivel] = useState(false);
    const [fichasVisiveis, setFichasVisiveis] = useState(false);

    function handleAtualizarMesa() {
        setModalVisivel(prev => !prev);
    }

    function handleExibirFichas() {
        setFichasVisiveis(prev => !prev);
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
                    <button onClick={handleExibirFichas}>
                        {fichasVisiveis ? "Ocultar Fichas" : "Exibir Fichas"}
                    </button>
                    <button onClick={handleAtualizarMesa}>Atualizar</button>
                    <button onClick={handleDeletarMesa}>Deletar</button>
                </div>
            </div>
            {fichasVisiveis && (
                <FichaList mesaId={mesa.id} />
            )}
            {modalVisivel && (
                <MesaForm onClose={handleAtualizarMesa} onMesasCriadas={onAlteradas} mesa={mesa} />
            )}
        </div>
    );
}

export default MesaCard;
