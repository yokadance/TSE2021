import React, { useEffect, useState } from 'react'
import {
  ChatList,
  MessageList,
  Input,
  Button,
} from './react-chat-elements/src'
import {BlockLoading} from 'react-loadingg'
import moment from "moment";

const ChatSideBar = ({ ci }) => {
  const [openChat, setOpenChat] = useState(false)

  const [contactId, setContactId] = useState(0)

  const [message, setMessage] = useState('')

  const [loading, setLoading] = useState(true)

  const [myId, setMyId] = useState(0)

  const [vaccinatorName, setVaccinatorName] = useState('')

  const [showChat, setShowChat] = useState(false)

  const [dataChatList, setDataChatList] = useState([
    {
      avatar: '',
      alt: '',
      title: '',
      subtitle: '',
      date: new Date(),
      unread: 0,
    },
  ])

  const [messageListData, setMessageListData] = useState([
    {
      position: '',
      type: '',
      text: '',
      date: new Date(),
    },
  ])

  async function getChatList() {
    let arrayChats = [
      {
        avatar: '',
        alt: '',
        title: '',
        subtitle: '',
        date: new Date(),
        unread: 0,
      },
    ]

    const mId = await getMyVaccinatorId()

    const url = 'https://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    let urlR = url + '/VacunasUYG16-api/api/chat/getlast/' + ci

    const responseR = await fetch(urlR, {
      crossDomain: true,
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const existingConversations = await responseR.json()

    arrayChats = existingConversations.map((c) => ({
      avatar:
        'https://st4.depositphotos.com/4329009/19956/v/600/depositphotos_199564354-stock-illustration-creative-vector-illustration-default-avatar.jpg',
      alt:
        'https://st4.depositphotos.com/4329009/19956/v/600/depositphotos_199564354-stock-illustration-creative-vector-illustration-default-avatar.jpg',
      title: c.contactName,
      subtitle: c.message,
      date: new Intl.DateTimeFormat('en-EN', {
        year: 'numeric',
        day: '2-digit',
        month: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
      }).format(new Date(c.timestamp)),
      unread: c.checked ? 1 : 0,
      id: mId !== c.transmitter_id ? c.transmitter_id : c.receiver_id,
    }))

    urlR = url + '/VacunasUYG16-api/api/vaccinator/lVaccinator'

    const responseV = await fetch(urlR, {
      crossDomain: true,
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const vaccinators = await responseV.json()
    let flgExiste = false

    for (let a = 0; a < vaccinators.length; a++) {
      flgExiste = false
      if (vaccinators[a].id !== mId) {
        // chequeo si el id es igual a "mi" id
        for (let i = 0; i < arrayChats.length; i++) {
          //chequeo si el id es igual a alguno de las conversaciones existentes
          if (vaccinators[a].id === arrayChats[i].id) {
            flgExiste = true
          }
        }

        if (!flgExiste) {
          // en caso de que no exista (false) agrego el vacunador al array de existingConversations
          arrayChats = [
            ...arrayChats,
            {
              avatar:
                'https://st4.depositphotos.com/4329009/19956/v/600/depositphotos_199564354-stock-illustration-creative-vector-illustration-default-avatar.jpg',
              alt:
                'https://st4.depositphotos.com/4329009/19956/v/600/depositphotos_199564354-stock-illustration-creative-vector-illustration-default-avatar.jpg',
              title:
                vaccinators[a].dtPerson.name +
                ' ' +
                vaccinators[a].dtPerson.lastname,
              subtitle: '',
              date: null,
              unread: 0,
              id: vaccinators[a].id,
            },
          ]
        }
      }
    }

    arrayChats.sort(function (a, b) {
      return Date.parse(new Date(a.date)) < Date.parse(new Date(b.date))
        ? 1
        : -1
    })

    setDataChatList(arrayChats)
    setLoading(false)
  }

  async function getMessageList(vid) {
    const url = 'https://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    const urlR = url + '/VacunasUYG16-api/api/chat/get/' + vid.id + '/' + myId

    const responseR = await fetch(urlR, {
      crossDomain: true,
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const messageListDataResp = await responseR.json()

    setMessageListData(
      messageListDataResp.map((c) => ({
        position: myId === c.transmitter_id ? 'right' : 'left',
        type: 'text',
        text: c.message,
        date: new Date(moment.utc(c.timestamp).local().format("YYYY-MM-DD HH:mm:ss")),
      })),
    )

    setVaccinatorName(vid.title)
    setContactId(vid.id)
    setShowChat(true)
    setOpenChat(true)
    scrollToBottom()
  }

  async function reloadMessageList() {
    const url = 'https://vacunasuyg16.web.elasticloud.uy'

    const urlR =
      url + '/VacunasUYG16-api/api/chat/get/' + contactId + '/' + myId

    const responseR = await fetch(urlR, {
      crossDomain: true,
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const messageListDataResp = await responseR.json()

    setMessageListData(
      messageListDataResp.map((c) => ({
        position: myId === c.transmitter_id ? 'right' : 'left',
        type: 'text',
        text: c.message,
        date: new Date(moment.utc(c.timestamp).local().format("YYYY-MM-DD HH:mm:ss")),
      })),
    )

    scrollToBottom()
  }

  async function sendMessage() {
    if (message !== '') {
      const url = 'https://vacunasuyg16.web.elasticloud.uy'
      // const url = 'http://localhost:8080'

      const axios = require('axios').default

      const msg = {
        message: message,
        transmitter_id: myId,
        receiver_id: contactId,
        checked: true,
        conversationId: '',
      }

      const sendMessageRequest = async () => {
        try {
          const resp = await axios.post(
            url + '/VacunasUYG16-api/api/chat/save',
            msg,
          )
          console.log(resp.data)
        } catch (err) {
          // Handle Error Here
          console.error(err)
        }
      }

      await sendMessageRequest()

      reloadMessageList()
      await getChatList()
    }
  }

  async function reloadOverTime() {
    if (openChat) {
      await getChatList()
      await reloadMessageList()
    }
  }

  let messagesEnd = React.createRef(null)
  let inputRef = React.createRef()

  const scrollToBottom = () => {
    messagesEnd.current?.scrollIntoView({ behavior: 'smooth' })
  }

  async function getMyVaccinatorId() {
    const url = 'https://vacunasuyg16.web.elasticloud.uy'
    // const url = 'http://localhost:8080'

    const urlR =
      url + '/VacunasUYG16-api/api/vaccinator/getVaccinatorIdByCi/' + ci

    const responseR = await fetch(urlR, {
      crossDomain: true,
      mode: 'cors',
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
      cache: 'default',
    })

    const vaccinatorData = await responseR.json()

    setMyId(vaccinatorData.id)

    return vaccinatorData.id
  }

  const timeMs = 5000

  useEffect(() => {
    getChatList()
    let interval
    if (openChat) {
      interval = setInterval(() => {
        reloadOverTime()
        console.log('Logs every 5 seconds')
      }, timeMs)
    } else {
      clearInterval(interval)
    }

    scrollToBottom()

    return () => {
      console.log('cleaned up')
      clearInterval(interval) // This represents the unmount function, in which you need to clear your interval to prevent memory leaks.
    }
  }, [openChat, contactId])

  return (
    <>
      {loading ? (
        <div className="w-full">
          <div className="grid justify-items-center mt-20">
            <BlockLoading style={{}} color="#75AFD6" size="large" />
          </div>
        </div>
      ) : (
        <div className="grid grid-rows-4 grid-flow-col gap-4 h-full h-4/5">
          <div className="row-span-4 border overflow-auto">
            <ChatList
              className=""
              dataSource={dataChatList}
              onClick={(id) => getMessageList(id)}
            />
          </div>
          {showChat ? (
            <div className="row-span-4 ">
              <div className="col-span-1 h-14 bg-denim-444 bg-opacity-75 border-b rounded-lg text-right mt-3 py-3">
                <label className="text-white mr-5 text-2xl">
                  {vaccinatorName}
                </label>
              </div>
              <div className="row-span-2 col-span-2 h-72 overflow-auto mt-4">
                <MessageList
                  dataSource={messageListData}
                  toBottomHeight={'100%'}
                  lockable={true}
                />
                <div ref={messagesEnd} />
              </div>
              <div className="row-span-1 col-span-1 border rounded-md w-12/12 mt-4">
                <form
                  className="rcw-sender"
                  onSubmit={(e) => {
                    e.preventDefault()
                    sendMessage()
                    inputRef.clear()
                  }}
                >
                  <Input
                    placeholder="Escriba aquí..."
                    multiline={false}
                    autofocus={true}
                    ref={(el) => (inputRef = el)}
                    onChange={(e) => setMessage(e.target.value)}
                    rightButtons={
                      <Button
                        color="white"
                        backgroundColor="denim-444"
                        text="Enviar"
                      />
                    }
                  />
                </form>
              </div>
            </div>
          ) : (
            <></>
          )}
        </div>
      )}
    </>
  )
}

export default ChatSideBar
