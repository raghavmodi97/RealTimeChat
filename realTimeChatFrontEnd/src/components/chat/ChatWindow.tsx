import * as React from "react";
import MessageList from "./MessageList"
import MessageInput from "./MessageInput"
import {useEffect, useState} from "react";
import {connectStomp, sendMessage, subscribeToConversation, subscribeToTyping} from "../../websocket/stompClient.ts";
import ChatHeader from "./ChatHeader.tsx";
import TypingIndicator from "./TypingIndicator.tsx";

export function ChatWindow({conversationId}:{conversationId:number}){
    //const conversationId=10;
    const myUserId=1;
    const [messages, setMessages] = useState([]);
    const [typingUser, setTypingUser] = useState(null);
    useEffect(()=>{

        connectStomp(()=>{
            subscribeToConversation(conversationId,(msg)=>{

                const dto=JSON.parse(msg.body);
                const incomingMsg={
                    senderId:dto.senderId,
                    senderName:dto.senderName,
                    content:dto.content,
                    createdAt:dto.createdAt,
                    tempId:dto.tempId
                }
                if (dto.senderId === myUserId) {
                    setMessages(prevMessages =>
                        prevMessages.map(m =>
                            m.tempId === dto.tempId
                                ? { ...m, status: "delivered" }
                                : m
                        )
                    );
                }
                //Why callback form?
                //Because WebSocket messages may arrive rapidly →
                // React needs the safest way to append.
                else {setMessages(messages=>[...messages,incomingMsg])};
            })
            subscribeToTyping(conversationId,(msg)=>{
                const dto = JSON.parse(msg.body);
                const incomingTyping=dto.typing;
                if(incomingTyping)setTypingUser(dto.senderId);
                else setTypingUser(null);
            })
        })
    },[])

    const handleSend = (text: string) => {
        const tempId=crypto.randomUUID();
        const msgBody={senderId:1,
            conversationId,content:text,tempId}
        sendMessage(conversationId,msgBody);
        const localMessage={
            senderId:1,
            tempId,
            senderName: "You",
            conversationId,
            content:text,
            createdAt:new Date().toISOString(),
            status:"sent"
        }
        setMessages(prev => [...prev, localMessage])

    };
    return (
        <div className={"chat-window"}>
            <ChatHeader conversationId={conversationId}/>
            {typingUser && <TypingIndicator />}
            <div className={"chat-body"}>
            <MessageList messages={messages} />
            </div>
            <MessageInput onSend={handleSend} />
        </div>
    );
}
