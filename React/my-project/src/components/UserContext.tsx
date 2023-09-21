import { createContext, useContext } from "react";

export type Content ={
    username:string,
    password:string,
}

export const UserContext = createContext<Content>({
    username:"",
    password:"",
})

export const useUserContext = () => useContext(UserContext)