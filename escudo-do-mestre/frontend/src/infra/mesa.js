const URL = 'http://localhost:8080/mesas/';

export async function createMesa(mesaData) {
    try {
        const response = await fetch(URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(mesaData),
        });
        if (!response.ok) {
            throw new Error('Erro ao criar mesa: ' + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Erro criando mesa:', error);
        throw error;
    }
}

export async function getMesas() {
    try {
        const response = await fetch(URL);
        if (!response.ok) {
            throw new Error('Erro ao buscar mesas: ' + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Erro buscando mesas:', error);
        throw error;
    }
}

export async function getMesaById(id) {
    try {
        const response = await fetch(`${URL}${id}`);
        if (!response.ok) {
            throw new Error('Erro ao buscar mesa: ' + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Erro buscando mesa:', error);
        throw error;
    }
}

export async function atualizarMesa(id, mesaData) {
    try {
        const response = await fetch(`${URL}${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(mesaData),
        });
        if (!response.ok) {
            throw new Error('Erro ao atualizar mesa: ' + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Erro atualizando mesa:', error);
        throw error;
    }
}

export async function deletarMesa(id) {
    try {
        const response = await fetch(`${URL}${id}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Erro ao deletar mesa: ' + response.statusText);
        }
        return true;
    } catch (error) {
        console.error('Erro deletando mesa:', error);
        throw error;
    }
}