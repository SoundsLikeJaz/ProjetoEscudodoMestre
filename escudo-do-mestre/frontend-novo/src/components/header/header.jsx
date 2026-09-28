import { useState } from "react";
import MesaForm from "../mesa-form/mesa-form";
import "./header.css"

function Header({ onMesasCriadas }) {

    const [modalVisivel, setModalVisivel] = useState(false);

    function handleCriarMesa() {
        setModalVisivel(prev => !prev);
    }

    return (
        <div>
            <header>
                <div className="title">
                    <h1>Escudo do Mestre</h1>
                </div>
                <div className="actions">
                    <button onClick={handleCriarMesa}>Crie sua mesa</button>
                </div>
            </header>
            {modalVisivel && (
                <MesaForm onClose={handleCriarMesa} onMesasCriadas={onMesasCriadas} />
            )}
        </div>
    );
}

export default Header;