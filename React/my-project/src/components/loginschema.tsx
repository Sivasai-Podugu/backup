import * as yup from "yup";
 
export const loginschema = yup
  .object({
    username: yup
      .string()
      .required("Username is required")
      .min(4, "Username must be at least 4 letters long")
      .max(10, "Username must not be 10 letters long"),
   
    password: yup
      .string()
      .required("Password is required")
      .min(4, "Password must be at least 4 letters long")
      .max(10, "Password must not be 10 letters long"),
    
  })
  .required();
