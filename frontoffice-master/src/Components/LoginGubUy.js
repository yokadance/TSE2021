import React, {Component} from 'react'
import {useJwt} from 'react-jwt'
import {removeCookie, setSession} from './SessionService'
import {Link, Redirect} from 'react-router-dom'
import Modal from './Modal'

const LoginGubUy = ({token, onChange}) => {

    const {decodedToken} = useJwt(token)

    function flgRefresh(flg) {
        onChange(flg, token)
    }

    function setNewUser() {
        removeCookie()
        decodedToken.newUser = false
        setSession(decodedToken)
        flgRefresh(true)
    }


    function setRole(val) {
        decodedToken.multirol = false
        decodedToken.role = val
        setSession(decodedToken)
        flgRefresh(true)
    }

    function redirect() {
        return <Redirect from={'/LoginGubUY'} exact to={'/'}/>
    }

    if (token !== null && decodedToken !== null) {
        if (checkToken(token)) {
            decodedToken.jwt = token

            // setSession(decodedToken)
            //TEST MULTIROL
            // decodedToken.multirol = true
            // decodedToken.role = ''
            //---

            //TEST NEW USER
            // decodedToken.newUser = true

            if (decodedToken.newUser === true) {
                setSession(decodedToken)
                return (
                    <Modal
                        Type={'usersettings'}
                        flgClose={false}
                        messageTitle={'BIENVENIDO A VACUNAS.UY POR FAVOR INGRESE SUS DATOS'}
                        onClick={() => setNewUser()}
                    />
                )
            }

            if (decodedToken.multirol === true) {
                //es multirol debo llamar una ventana para seleccionar rol
                return (
                    <Modal
                        Type={'multirole'}
                        flgClose={false}
                        onClick={(val) => setRole(val)}
                    />
                )
            } else {
                setSession(decodedToken)
                flgRefresh(true)
                return <Redirect from={'/LoginGubUY'} exact to={'/'}/>
            }
        } else {
            console.log('Token no valido')
        }
    }

    return <></>
}

const checkToken = async (token) => {
    const url = 'http://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    //Chequeo que el token sea correcto
    const urlRedirect =
        url + '/VacunasUYG16-api/api/login/checkLoginGubUY/' + token

    const response = await fetch(urlRedirect, {
        mode: 'cors',
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        cache: 'default',
    })

    if (response.status === 202)
        //OK
        return true
    else return false
}

export default LoginGubUy
