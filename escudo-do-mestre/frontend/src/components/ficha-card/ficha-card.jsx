import { useState } from "react";
import { deletarFicha } from "../../infra/ficha";
import FichaForm from "../ficha-form/ficha-form";
import "./ficha-card.css"

function FichaCard({ ficha, mesaId, onFichasAlteradas }) {

    const [modalVisivel, setModalVisivel] = useState(false);

    function handleAtualizarFicha() {
        setModalVisivel(prev => !prev);
    }

    async function handleDeletarFicha() {
        await deletarFicha(ficha.id);
        alert("Ficha deletada com sucesso!");
        onFichasAlteradas();
    }

    return (
        <div>
            <div className="ficha-card">
                <div>
                    <h3>{ficha.nome}</h3>
                    <p>Jogador: {ficha.jogador}</p>
                    <p>Nível {ficha.nivel} — {ficha.ancestralidade}</p>
                    <p>{ficha.aprendiz} / {ficha.especialista}</p>
                    <div className="ficha-atributos">
                        <span>PODER {ficha.poder}</span>
                        <span>INT {ficha.intelecto}</span>
                        <span>AGI {ficha.agilidade}</span>
                        <span>FOR {ficha.forca}</span>
                        <span>SAÚDE {ficha.saude}</span>
                        <span>VONT {ficha.vontade}</span>
                        <span>PERC {ficha.percepcao}</span>
                        <span>DEF {ficha.defesa}</span>
                    </div>
                </div>
                <div>
                    <button onClick={handleAtualizarFicha}>Atualizar</button>
                    <button onClick={handleDeletarFicha}>Deletar</button>
                </div>
            </div>
            {modalVisivel && (
                <FichaForm
                    onClose={handleAtualizarFicha}
                    onFichasAlteradas={onFichasAlteradas}
                    mesaId={mesaId}
                    ficha={ficha}
                />
            )}
        </div>
    );
}

export default FichaCard;
