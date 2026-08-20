const URL = 'http://localhost:8080/fichas';

export async function createFicha(fichaData, mesaId) {
    try {
        const response = await fetch(`${URL}/${mesaId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(fichaData),
        });
        if (!response.ok) {
            throw new Error('Erro ao criar ficha: ' + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Erro criando ficha:', error);
        throw error;
    }
}

export async function getFichasByMesaId(mesaId) {
    try {
        const response = await fetch(`${URL}/mesa/${mesaId}`);
        if (!response.ok) {
            throw new Error('Erro ao buscar fichas: ' + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Erro buscando fichas:', error);
        throw error;
    }
}

export async function getFichaById(id) {
    try {
        const response = await fetch(`${URL}/${id}`);
        if (!response.ok) {
            throw new Error('Erro ao buscar ficha: ' + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Erro buscando ficha:', error);
        throw error;
    }
}

export async function atualizarFicha(id, fichaData) {
    try {
        const response = await fetch(`${URL}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(fichaData),
        });
        if (!response.ok) {
            throw new Error('Erro ao atualizar ficha: ' + response.statusText);
        }   
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Erro atualizando ficha:', error);
        throw error;
    }
}

export async function deletarFicha(id) {
    try {
        const response = await fetch(`${URL}/${id}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Erro ao deletar ficha: ' + response.statusText);
        }
    } catch (error) {
        console.error('Erro deletando ficha:', error);
        throw error;
    }
}