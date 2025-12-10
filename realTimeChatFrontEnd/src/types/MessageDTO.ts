export interface MessageDTO {
    id?: number;
    conversationId: number;
    senderId: number;
    senderName: string;
    content: string;
    createdAt: string;
}