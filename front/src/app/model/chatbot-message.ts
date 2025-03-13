import { Response } from './response';

export interface ChatbotMessage {
  sender: string;
  content: string;
  timestamp: Date;
  response?: Response[];
}
