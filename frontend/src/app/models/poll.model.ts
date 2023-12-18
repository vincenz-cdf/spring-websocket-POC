import { Option } from "./option.model";

export interface Poll {
    id: number;
    question: string;
    options: Option[];
}