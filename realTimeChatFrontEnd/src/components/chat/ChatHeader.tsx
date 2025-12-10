export default function ChatHeader({conversationId}){
    return(
        <div className={"chat-header"}>
            <button className={"back-button"}>Back</button>
            <div className={"chat-title"}>{`Conversation ${conversationId}`}</div>
            <div className={"user-status"}>Online</div>
        </div>
    )
}