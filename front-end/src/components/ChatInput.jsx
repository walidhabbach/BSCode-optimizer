import React, { useState, useRef, useEffect } from 'react';
import send from '../assets/send-icon.png';
import loadingIcon from '../assets/loading.gif';

const ChatInput = ({ sendMessage, loading, maxHeight = 200 }) => {
  const [value, setValue] = useState('');
  const textAreaRef = useRef(null);

  useEffect(() => {
    if (textAreaRef.current) {
      // Reset height to auto to calculate scrollHeight correctly
      textAreaRef.current.style.height = 'auto';
      // Set height to scrollHeight or maxHeight, whichever is smaller
      textAreaRef.current.style.height = `${Math.min(textAreaRef.current.scrollHeight, maxHeight)}px`;
    }
  }, [value, maxHeight]);

  const handleSubmit = async () => {
    if (value === '') return;
    await sendMessage(value);
    setValue('');
  };

  return (
    <div className="w-full bg-white bg-opacity-10 max-h-40 rounded-lg px-4 py-4 overflow-auto relative">
      {loading ? (
        <img src={loadingIcon} alt="Loading" className="w-8 m-auto" />
      ) : (
        <>
          <textarea
            ref={textAreaRef}
            rows={1}
            className="border-0 bg-transparent outline-none w-11/12 overflow-hidden"
            value={value}
            onKeyDown={(e) => {
              if (e.keyCode === 13 && !e.shiftKey) handleSubmit();
            }}
            onChange={(e) => setValue(e.target.value)}
          />
          <button
            className="absolute top-4 right-3 hover:cursor-pointer ease-in duration-100 hover:scale-125"
            onClick={handleSubmit}
          >
            <svg
              stroke="currentColor"
              fill="none"
              strokeWidth="2"
              viewBox="0 0 24 24"
              strokeLinecap="round"
              strokeLinejoin="round"
              className="h-6 w-6 mr-1"
              height="1em"
              width="1em"
              xmlns="http://www.w3.org/2000/svg"
            >
              <line x1="22" y1="2" x2="11" y2="13"></line>
              <polygon points="22 2 15 22 11 13 2 9 22 2"></polygon>
            </svg>
          </button>
        </>
      )}
    </div>
  );
};

export default ChatInput;
