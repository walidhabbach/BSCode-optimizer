import { useNavigate } from "react-router";
import { login } from "../../services/authService";
import styles from "./AuthPage.module.css";
import {  useState } from "react";
 const AuthPage = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
 
    const navigate = useNavigate();

     
    const handleSubmit = async (e) => {
        try {
            e.preventDefault();
            const response = await login(email, password);
            console.log(response);

            localStorage.setItem('token', response.access_token);

          
            localStorage.setItem('email', JSON.stringify(email));
             navigate('/');

        } catch (error) {
            setError("Login failed");
        }
    };


    return (
        <div className={styles.authPage}>
            <form onSubmit={handleSubmit} className={styles.loginForm}>
                {error && <div className={styles.errorMessage}>{error}</div>}
                <div className={styles.formGroup}>
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div className={styles.formGroup}>
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className={styles.submitButton}>Login</button>
                <a className={styles.alreadyLink} href="/register">Not a member ?</a>
            </form>
        </div>
    );
};

export default AuthPage;
