import { jwtDecode } from 'jwt-decode'; // Correct named import

const Token_KEY = 'token';
const Refresh_Token_KEY = 'refresh-token';

class TokenService {
  // Save tokens to localStorage
  static saveToken(tokens) {
    if (tokens && tokens.access_token && tokens.refresh_token) {
      console.log(tokens);
      localStorage.setItem(Token_KEY, tokens.access_token);
      localStorage.setItem(Refresh_Token_KEY, tokens.refresh_token);
    } else {
      console.error('Invalid token structure', tokens);
    }
  }

  // Decode JWT token
  static decodeToken(token) {
    try {
      return jwtDecode(token); // Use named import here
    } catch (error) {
      console.error('Invalid token', error);
      return null;
    }
  }

  // Remove tokens from localStorage
    static removeTokens() {
      console.log(Token_KEY)
    localStorage.removeItem(Token_KEY);
    localStorage.removeItem(Refresh_Token_KEY);
  }

  // Get the stored token or default to an empty string
  static getToken() {
    return localStorage.getItem(Token_KEY) || '';
  }

  // Get email from decoded token
  static getEmail() {
    const token = this.getToken();
    return token ? this.decodeToken(token)?.email ?? null : null;
  }

  // Get user ID from decoded token
  static getUserId() {
    const token = this.getToken();
    return token ? this.decodeToken(token)?.id ?? null : null;
  }

  // Check if the token is valid and not expired
  static check() {
    const token = this.getToken();
    return token && !this.isTokenExpired(token);
  }

  // Check if the token is expired
  static isTokenExpired(token, offsetSeconds = 0) {
    if (!token) return true;

    const expirationDate = this._getTokenExpirationDate(token);
    
    return !expirationDate || expirationDate.valueOf() <= new Date().valueOf() + offsetSeconds * 1000;
  }

  // Retrieves the token's expiration date
  static _getTokenExpirationDate(token) {
    const decodedToken = this.decodeToken(token);

    if (!decodedToken?.exp) return null;

    const expirationDate = new Date(0);
    expirationDate.setUTCSeconds(decodedToken.exp);

    return expirationDate;
  }
}

export default TokenService;
