import axios from 'axios';

export const login = async(email,password) =>{
 

    try {
        const response = await axios.post(import.meta.env.VITE_API_URL + "/api/auth/signin", {
            email,
            password
        }
        ,
            {
                headers: {
                    "Content-Type": "application/json"
                }
            }); 
        if (response.status === 200) {
            
            console.log("Login successful:", response.data);
            
            return response.data;
        } else {
            throw new Error(`SIGNIN failed with status: ${response.status}`);
        }
    } catch (error) {
        console.log(error)
        throw new Error(`Error: ${error.response ? error.response.data : error.message}`);
    }
}
export const signup = async(data) =>{
 
     try {
        console.log(data)
         const response = await axios.post(import.meta.env.VITE_API_URL + "/api/auth/signup", {
             "firstname": data["firstName"],
             "lastname": data["lastName"],
             "email": data["email"],
             "password": data["password"],
             "gender": data["gender"] == "male" ? 1 : 0
         }
        ,
        { 
            headers:{
                "Content-Type": "application/json"
            } 
        }
    )
            console.log(response);

        if (response.status === 201) {
            console.log("Signup successful:", response.data);
        
            return response.data;
        } else {
            throw new Error(`Signup failed with status: ${response.status}`);
        }
    } catch (error) {
        throw new Error(`Error: ${error.response ? error.response.data : error.message}`);
    } 
}