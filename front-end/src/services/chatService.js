import axios from 'axios';
import TokenService from './tokenService';

export const optimize = async (input) => {
 
 
    try {
         
        const token = TokenService.getToken();
       const response = await axios.post(
            `${import.meta.env.VITE_API_URL}/api/chat/predict`,
          input,
            {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
            }
        );
         console.log(response.data);
         return response.data
    } catch (error) {
        if (error.response) {
            console.error("Server Response:", error.response.data);
        } else {
            console.error("Error Message:", error.message);
        }
        throw error;
    }
};
export const fetchChat = async () => {
    const token = TokenService.getToken();
       const userId = TokenService.getUserId();
    try {
       const response = await axios.get(
            `${import.meta.env.VITE_API_URL}/api/chat/user/${userId}`, 
            {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
            }
        );
         console.log(response.data);
         return response.data
    } catch (error) {
        if (error.response) {
            console.error("Server Response:", error.response.data);
        } else {
            console.error("Error Message:", error.message);
        }
        throw error;
    }
  
}