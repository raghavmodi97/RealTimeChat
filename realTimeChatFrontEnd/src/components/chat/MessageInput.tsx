    import {useState,useRef} from "react";
    import {sendTypingEvent} from "../../websocket/stompClient.ts";

    export default function MessageInput({onSend}){
        const [text,setText]=useState("");
        const textAreaRef =useRef(null);
        const typingRef=useRef(false);
        const stopTypingTimerRef = useRef<number | undefined>(undefined);
        function handleMessage(){
            if(text.trim()==="")return;
            onSend(text);
            setText("");
            // shrink textarea back to one line
            const element = textAreaRef.current;
            if (element) {
                element.style.height = "auto";
            }
        }
        function autoGrow(){
            const element=textAreaRef?.current
            if(!element)return;
            element.style.height="auto";
            element.style.height=element.scrollHeight + "px";

        }
        function sendTyping(isTyping){
            const typingEventBody={
                senderId:1,
                conversationId:10,
                typing:isTyping,
            }
            sendTypingEvent(10,typingEventBody);
        }
        function onKeyDown(e){
            // 1. START TYPING
            if(!typingRef.current){
                typingRef.current=true;
                sendTyping(true);
            }
            // 2. RESET STOP-TYPING TIMER
            clearTimeout(stopTypingTimerRef.current);
            stopTypingTimerRef.current=setTimeout(()=>{
                typingRef.current=false;
                sendTyping(false);
            },1000)
            if(e.key==="Enter"){
                if(!e.shiftKey){
                    e.preventDefault();
                    handleMessage();
                }
                return;
            }

        }
        return (
            <div className={"input-row"}>
            <textarea ref={textAreaRef} value={text} placeholder="Type your message..." onKeyDown={onKeyDown} onChange={(e)=>{setText(e.target.value);autoGrow();}}/>
            <button onClick={handleMessage}>Send</button>
            </div>
        )
    }