import { Cookies } from 'react-cookie'

const cookies = new Cookies()

export const setSession = (sessionData) => {
  cookies.set('__FOsession', sessionData, { path: '/' })
}

export const getSession = () => {
  // const [cookies, setCookie] = useCookies(['session']);
  // const jwt = Cookies.get('__session')
  const jwt = ''
  let session
  try {
    if (jwt) {
      // COMENTO LO DE ABAJO MIENTRAS NO CONECTAMOS CON LA API
      // const base64Url = jwt.split('.')[1]
      // const base64 = base64Url.replace('-', '+').replace('_', '/')
      // // what is window.atob ?
      // // https://developer.mozilla.org/en-US/docs/Web/API/WindowOrWorkerGlobalScope/atob
      // session = JSON.parse(window.atob(base64))
      return jwt
    } else {
      return null
    }
  } catch (error) {
    console.log(error)
  }
  return session
}

export const removeCookie = () => {
  cookies.remove('__FOsession', { path: '/' })
}

export const logOut = async (idToken) => {
  const redirect_uri =
    'http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/logingubuy/logout'

  const encodeUri = encodeURI(redirect_uri)

  const inputForm =
    'id_token_hint=' +
    idToken +
    '&post_logout_redirect_uri=' +
      encodeUri +
    '&state='

  const url =
    'https://auth-testing.iduruguay.gub.uy/oidc/v1/logout?' + inputForm

  cookies.remove('__FOsession', { path: '/' })

  window.location.href = url

  // MakuGobUy@1707
}
