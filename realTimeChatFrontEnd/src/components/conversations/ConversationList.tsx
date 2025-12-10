import {useState,useEffect} from "react";

type ConversationSummary={
    id:number;
    title:string;
    createdAt:string;
}
type ConversationListProps={
    onSelect:(id:number)=>void;
    selectedId?:number
}

export function ConversationList({onSelect,selectedId}:ConversationListProps){
    // Local state: list of conversations
    const [conversations, setConversations] = useState<ConversationSummary[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    useEffect(() => {
            async function load() {
                try {
                    const res = await fetch("http://localhost:8080/api/conversations/user/1");

                    if (!res.ok) {
                        throw new Error("Failed to load conversations");
                    }

                    const data = await res.json(); // this will be an array of ConversationSummary
                    setConversations(data);
                } catch (err) {
                    setError("Could not load conversations");
                } finally {
                    setLoading(false);
                }
            }
            load();
    }, []);
    if (loading) {
        return <div className="conversation-list">Loading...</div>;
    }

    // 7️⃣ If error happened, show message
    if (error) {
        return <div className="conversation-list">{error}</div>;
    }
    return <div className={"conversation-list"}>
        {conversations.map((c)=>{
            const isSelected = selectedId===c.id;

            return <div
                key={c.id}
                className={"conversation-item" + (isSelected ? " conversation-item-selected":"")}
                onClick={()=>{onSelect(c.id)}}>
                <div className={"conversation-title"}>{c.title}</div>
                <div className={"conversation-subtitle"}>{new Date(c.createdAt).toLocaleTimeString([], {
                    hour: "2-digit",
                    minute: "2-digit",
                })}</div>

            </div>
        })}
    </div>
}