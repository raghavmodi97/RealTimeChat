import {ChatWindow} from "./components/chat/ChatWindow.tsx";
import {ToastContainer} from "react-toastify";
import {useState} from "react";
import "react-toastify/dist/ReactToastify.css";
import './App.css';
import {ConversationList} from "./components/conversations/ConversationList.tsx";
function App() {
    const [selectedId, setSelectedId] = useState<number | null>(null);
    return (
        <>
            <div className={"app-layout"}>
                <ConversationList selectedId={selectedId} onSelect={(id:number)=>setSelectedId(id)} />

                <div className={"app-chat-pane"}>
                {selectedId?(<ChatWindow conversationId={selectedId}/>):(<div>"Select a conversation to start chatting"</div>)}
                </div>
            <ToastContainer position="bottom-center" autoClose={2000} />
            </div>
        </>
    )

}

export default App;
