import React, { createContext, useState } from 'react';

export const AuthContext = createContext({});

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const register = async (userData) => {
    setIsLoading(true);
    try {
      // Aquí iría la lógica de registro con tu backend
      // Por ahora guardamos localmente
      setUser(userData);
      return { success: true };
    } catch (error) {
      return { success: false, error: error.message };
    } finally {
      setIsLoading(false);
    }
  };

  const login = async (emailOrUsername, password) => {
    setIsLoading(true);
    try {
      // Aquí iría la lógica de login con tu backend
      // Por ahora verificamos localmente
      setUser({ emailOrUsername, password });
      return { success: true };
    } catch (error) {
      return { success: false, error: error.message };
    } finally {
      setIsLoading(false);
    }
  };

  const logout = () => {
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, isLoading, register, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
