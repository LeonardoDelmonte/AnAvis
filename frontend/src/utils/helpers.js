//-----Alert
import { confirmAlert } from 'react-confirm-alert';
//-----JWT-----
import jwt from 'jwt-decode'

//function
export function ShowSimpleAlert(message) {
    confirmAlert({
        message: message,
        buttons: [
            {
                label: 'Ok',
            },
        ],
    });
}

export function controllPassword(password, rpassword) {
    const reg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/;
    const test = reg.test(password);
    if (!test) {
        ShowSimpleAlert('La password deve essere composta da almeno 8 caratteri, una lettera minuscola, una lettera maiuscola, un numero e un carattere speciale')
        return false;
    }
    if (password !== rpassword) {
        ShowSimpleAlert('Le due password non corrispondono')
        return false;
    }
    return true
}

export function isLogged() {
    if (localStorage.getItem('Authorization') && jwt(localStorage.getItem('Authorization')).exp > Date.now() / 1000 | 0)
        return true
    return false;
}

export function isDonatore() {
    if (isLogged() && jwt(localStorage.getItem('Authorization')).aud === "donatore") 
        return true
    return false;
}

export function isSede() {
    if (isLogged() && jwt(localStorage.getItem('Authorization')).aud === "sedeAvis") 
        return true;
    return false;
}

export function isCentro() {
    if (isLogged() && jwt(localStorage.getItem('Authorization')).aud === "centroTrasfusione") 
        return true;
    return false;
}

export function dateToString(date) {
    return date.getDate() + "." + date.getMonth() + 1 + "." + date.getFullYear();
}

export function timeToString(date) {
    return date.getHours() + ":" + date.getMinutes();
}




//constant 
export const optionsGruppoSanguigno = [
    { value: '0 Rh-', label: '0 Rh-' },
    { value: '0 Rh+', label: '0 Rh+' },
    { value: 'A Rh-', label: 'A Rh-' },
    { value: 'A Rh+', label: 'A Rh+' },
    { value: 'B Rh-', label: 'B Rh-' },
    { value: 'B Rh+', label: 'B Rh+' },
    { value: 'AB Rh-', label: 'AB Rh-' },
    { value: 'AB Rh+', label: 'AB Rh+' }
]

export const optionsSiNo = [
    { value: 'Si', label: 'Si' },
    { value: 'No', label: 'No' }
]

