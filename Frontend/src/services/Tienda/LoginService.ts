export const loguearse = async (email: string, pwd: string) => {
    try{
        const res = await fetch('http://localhost:8081/login', {    
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email,
                pwd: pwd
            })
        });
        const data = await res.json();

        //Guardamos el usuario en el navegador
        /*localStorage.setItem('usuario_redpanda', JSON.stringify({
            id: data.id,
            nombre: data.nombre,
            email: data.email
        }));*/

        return data;
    } catch (error) {
        return null;
    }
};

export const registrar = async (nombre: string, email: string, pwd: string) => {
    try{
        const res = await fetch('http://localhost:8081/register', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                nombre: nombre,
                email: email,
                pwd: pwd
            })
        });
        const data = await res.json();

        //Guardamos el usuario en el navegador
       /* localStorage.setItem('usuario_redpanda', JSON.stringify({
            id: data.id,
            nombre: data.nombre,
            email: data.email,
            preferencia_idioma: data.preferencia_idioma
        }));*/

        return data;
    } catch (error) {
       return null
    }
};


export const desloguearse = async () => {
    try{
        const res = await fetch('http://localhost:8081/logout', {    
            method: 'POST', 
            credentials: 'include'
        });
        const data = await res.json();

        
        return data;
    } catch (error) {
        console.error('Error al cerrar sesión:', error);
        throw error;
    }
};
