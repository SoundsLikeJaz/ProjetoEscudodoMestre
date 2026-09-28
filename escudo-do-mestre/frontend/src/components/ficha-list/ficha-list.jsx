import { useEffect, useState } from "react";
import { getFichasByMesaId } from "../../infra/ficha";
import FichaCard from "../ficha-card/ficha-card";
import FichaForm from "../ficha-form/ficha-form";
import "./ficha-list.css"

function FichaList({ mesaId }) {

    const [fichas, setFichas] = useState([]);
    const [modalVisivel, setModalVisivel] = useState(false);

    async function carregarFichas() {
        const dados = await getFichasByMesaId(mesaId);
        setFichas(dados);
    }

    useEffect(() => {
        let ativo = true;
        (async () => {
            const dados = await getFichasByMesaId(mesaId);
            if (ativo) setFichas(dados);
        })();
        return () => { ativo = false; };
    }, [mesaId]);

    function handleCriarFicha() {
        setModalVisivel(prev => !prev);
    }

    return (
        <div className="ficha-list">
            <div className="ficha-list-header">
                <h3>Fichas da mesa</h3>
                <button onClick={handleCriarFicha}>Nova Ficha</button>
            </div>
            <div className="ficha-list-cards">
                {fichas?.length > 0 ? (
                    fichas.map(ficha => (
                        <FichaCard
                            key={ficha.id}
                            ficha={ficha}
                            mesaId={mesaId}
                            onFichasAlteradas={carregarFichas}
                        />
                    ))
                ) : (
                    <p>Nenhuma ficha cadastrada nesta mesa.</p>
                )}
            </div>
            {modalVisivel && (
                <FichaForm
                    onClose={handleCriarFicha}
                    onFichasAlteradas={carregarFichas}
                    mesaId={mesaId}
                />
            )}
        </div>
    );
}

export default FichaList;
