import { useRef, useEffect } from "react";

export default function MessageList({ messages }) {
    const myUserId = 1;

    const bottomRef = useRef(null); // 1️⃣ define first

    function formatTime(isoString) {
        if (!isoString) return "";
        const date = new Date(isoString);
        return date.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
    }

    useEffect(() => {
        bottomRef.current?.scrollIntoView({ behavior: "smooth" });
    }, [messages]);                // 2️⃣ effect runs after message updates

    return (
        <div className="messageListPlaceholder">
            {messages.map((msg, index) => {
                return (
                    <div
                        className={`message ${msg.senderId === myUserId ? "right" : "left"}`}
                        key={index}
                    >
                        <div className="msg-content">
                            {msg.senderName}: {msg.content}
                        </div>

                        <div className="msg-meta">
                            <span className="timestamp">{formatTime(msg.createdAt)}</span>

                            {msg.senderId === myUserId && (
                                <span className="status">
                        {msg.status === "sent" ? "✓" : "✓✓"}
                    </span>
                            )}
                        </div>
                    </div>
                );
            })}

            <div ref={bottomRef}></div>  {/* 3️⃣ scroll target */}
        </div>
    );
}
