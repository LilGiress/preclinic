import { UserResponse } from "../userResponse";

export interface PageResponseUserResponse {
    content?: Array<UserResponse>,
    first?: boolean;
    last?:boolean;
    number?:number;
    size?:number;
    totalElements?:number;
    totalPages?:number;
}