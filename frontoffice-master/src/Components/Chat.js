import React, {useEffect, useState} from 'react'
import ChatSideBar from './ChatSideBar'
import { Cookies } from 'react-cookie'
import Title from './Title'
import swal from '@sweetalert/with-react'
import { Link, Redirect } from 'react-router-dom'

const Chat = () => {
  const [roleOk, setRoleOk] = useState(false)
  const c = new Cookies()

  const getCook = () => {
    if (c.get('__FOsession') !== undefined) {
      return c.get('__FOsession')
    }
  }

  const getCi = () => {
    if(c.get('__FOsession') !== undefined) {
      if (cook.ci.toString() !== undefined) {
        return cook.ci.toString()
      }
    }
  }

  const cook = getCook()

  const ci = getCi()

  // const cook = c.get('__FOsession')
  //
  // const ci = cook.ci.toString()

  useEffect(() => {
    if (c.get('__FOsession') !== undefined) {
      const cook = c.get('__FOsession')

      if (cook !== undefined) {
        if (cook.role.toString() !== 'Vaccinator') {
          //NO TIENE ROL PARA INGRESAR A ESTA VENTANA
          return swal(
            'Usted no tiene permisos para acceder a esta funcionalidad',
            'Será redirigido a la página principal',
            'error',
            { button: 'Continuar' },
          ).then((value) => {
            window.location.href = '/'
            // return <Redirect exact to="./" />
          })
        }
      }
    } else {
      //NO TIENE SESIÓN HECHA
      return swal(
        'Usted no tiene permisos para acceder a esta funcionalidad',
        'Será redirigido a la página principal',
        'error',
        { button: 'Continuar' },
      ).then((value) => {
        window.location.href = '/'
        // return <Link to="./" />
      })
    }
    setRoleOk(true)
  }, [])

  return (
    <>
      <div className="grid justify-items-center">
        <Title title="Mensajería" />
      </div>
      <div>
        {roleOk ? <ChatSideBar ci={ci} /> : <></>}
      </div>
    </>
  )
}

export default Chat
