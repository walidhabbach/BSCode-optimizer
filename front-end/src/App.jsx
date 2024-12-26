import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate for navigation
import Body from './components/Body';
import { useMutation } from 'react-query';
import ChatInput from './components/ChatInput';
import { fetchChat, optimize } from './services/chatService';
import TokenService from './services/tokenService';

function App() {
  const [chat, setChat] = useState([]);
  const navigate = useNavigate(); // Initialize navigate for routing

  const mutation = useMutation({
    mutationFn: () => fetchChat(),
    onSuccess: (data) => {
      setChat(data);
    },
  });

  const mutationSend = useMutation({
    mutationFn: (input) => optimize(input),
    onSuccess: (data) => {
      setChat((prev) => [...prev, data]);
    },
  });

  const sendMessage = async (message) => {
    const input = { code: message, isInput: true, idUser: TokenService.getUserId() };
    setChat((prev) => [...prev, input]); // Add input message to chat
    mutationSend.mutate(input); // Send the message to optimize
  };

  const logout = () => { 
    TokenService.removeTokens(); // Remove the tokens
    navigate('/login'); // Redirect to login page
  };

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token == null) {
      navigate('/login');
    }
    mutation.mutate();
  }, [navigate]);

  return (
    <div className="bg-[#343541] h-screen py-6 relative sm:px-10 px-5 text-white overflow-hidden flex flex-col justify-between align-middle">
      {/* Gradients */}
      <div className="gradient-01 z-0 absolute"></div>
      <div className="gradient-02 z-0 absolute"></div>
 
      <div className="relative flex justify-center items-center mb-3 w-full">
        {/* Title */}
        <div className="uppercase font-bold text-2xl text-center flex-grow">
          BSCode 1.0
        </div>

        {/* Logout Button */}
        <button
          onClick={logout}
          className="absolute right-4 top-1/2 transform -translate-y-1/2 bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition"
        >
          Logout
        </button>
      </div>

      {/* Body */}
      <div className="h-[90%] overflow-auto w-full max-w-4xl min-w-[20rem] py-8 px-4 self-center scrollbar-thumb-slate-400 scrollbar-thin scrollbar-track-gray-transparent scrollbar-thumb-rounded-md">
        <Body chat={chat} />
      </div>

      {/* Input */}
      <div className="w-full max-w-4xl min-w-[20rem] self-center scrollbar-thumb-slate-400 scrollbar-thin scrollbar-track-gray-transparent scrollbar-thumb-rounded-md">
        <ChatInput sendMessage={sendMessage} loading={mutationSend.isLoading} />
      </div>

      {/* Footer */}
      <div className="text-center text-xs text-white mt-4">
        <small>
          AI data powered by
          <span className="underline ml-1">
            <a target="_blank" rel="noopener noreferrer">
              BS-Simplify
            </a>
          </span>
          . &copy; Developed By: Ahmed Messoudi
        </small>
      </div>
    </div>
  );
}

export default App;
