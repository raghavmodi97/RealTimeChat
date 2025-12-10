import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import {toast} from "react-toastify"
let stompClient: Client | null = null;

// The callback here fires when STOMP is CONNECTED (not message received)
export const connectStomp = (onConnectedCallback: () => void) => {
    const socket = new SockJS("http://localhost:8080/ws");

    stompClient = new Client({
        webSocketFactory: () => socket as any,
        reconnectDelay: 5000,//If your server restarts, the frontend reconnects automatically every 5 seconds.
        debug: (str) => console.log(str),

        onConnect: () => {
            console.log("STOMP connected");

            // 🔥 MOST IMPORTANT FIX
            onConnectedCallback();
        },
    });

    stompClient.onStompError = (frame) => {
        try{
            const parsedData=JSON.parse(frame.body);
            const messsage:any=parsedData.error || parsedData.message || frame.body;
            toast.error(messsage);
        }
        catch(err){
        console.log(err);
        toast.error(frame.body);
        }

    };

    stompClient.onWebSocketError = (event) => {
        toast.error("Websocket connection error")
    };

    stompClient.activate();
};
//What this does:
//React calls this function when it wants to listen for messages.
//It subscribes to the topic in your backend:
// =>  /topic/conversations.10
//Whenever the backend broadcasts a new message, callback(msg) is executed.
//This is EXACTLY like:
//messagingTemplate.convertAndSend("/topic/conversations.10", dto);
//on the backend.
export const subscribeToConversation = (
    conversationId: number,
    callback: (msg: any) => void
) => {
    return stompClient?.subscribe(
        `/topic/conversations.${conversationId}`,
        callback
    );
};

export const subscribeToTyping=(conversationId:number,callback:(msg:any)=>void)=>{
    return stompClient?.subscribe(
        `/topic/typing.${conversationId}`,callback
    )
}
//This sends a message to our backend mapping:
//@MessageMapping("/chat/{conversationId}")
//When we call:
//sendMessage(10, { content: "Hi" })
//STOMP actually sends:
/*SEND
destination:/app/chat/10
{"content":"Hi"}
^@
*/
//ChatController receives it, saves it, broadcasts it.
export const sendMessage = (conversationId: number, body: any) => {
    stompClient?.publish({
        destination: `/app/chat/${conversationId}`,
        body: JSON.stringify(body),
    });
};
export const sendTypingEvent=(conversationId:number,body:any)=>{
    stompClient?.publish({
        destination: `/app/typing/${conversationId}`,
        body: JSON.stringify(body),
    });
}