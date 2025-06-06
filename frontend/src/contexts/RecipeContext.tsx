
import React, { createContext, useContext, useState, useEffect } from 'react';
import { useAuth } from './AuthContext';
import axios from 'axios';

const baseUrl = import.meta.env.VITE_API_BASE_URL;

export interface Recipe {
  id: number;
  userId: number;
  name: string;
  description: string;
  url: string;
  tags: string[];

}

interface RecipeContextType {
  recipes: Recipe[];
  isLoading: boolean;
  searchTerm: string;
  setSearchTerm: (term: string) => void;
  filteredRecipes: Recipe[];
  addRecipe: (recipe: Omit<Recipe, 'id' | 'userId'>) => Promise<void>;
  deleteRecipe: (id: number) => Promise<void>;
  refreshRecipes: () => Promise<void>;
}

const RecipeContext = createContext<RecipeContextType | undefined>(undefined);

export function RecipeProvider({ children }: { children: React.ReactNode }) {
  const { user } = useAuth();
  const [recipes, setRecipes] = useState<Recipe[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');

  const filteredRecipes = recipes.filter(recipe => {
    const searchLower = searchTerm.toLowerCase();
    return (
      recipe.name.toLowerCase().includes(searchLower) ||
      recipe.description.toLowerCase().includes(searchLower) ||
      recipe.tags.some(tag => tag.toLowerCase().includes(searchLower))
    );
  });

  const fetchRecipes = async () => {
    if (!user) return;
    
    setIsLoading(true);
    try {
      const token = localStorage.getItem('authToken');
       const {data} = await axios.get(`${baseUrl}/api/users/${user.id}/recipes`,{
         headers: {
          'Authorization': `Bearer ${token}`,
        },
      })
      setRecipes(data|| []);
      
    } catch (error) {
      console.error('Failed to fetch recipes:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const addRecipe = async (recipeData: Omit<Recipe, 'id' | 'userId'>) => {
    if (!user) throw new Error('User not authenticated');

    try {
      const token = localStorage.getItem('authToken');
      const {data} = await axios.post(`${baseUrl}/api/users/${user.id}/recipes`,recipeData,{
         headers: {
          'Authorization': `Bearer ${token}`,
        },
      })
      const newRecipe = data;
      setRecipes(prev => [newRecipe, ...prev]);
    } catch (error) {
      console.error('Failed to add recipe:', error);
      throw error;
    }
  };

  const deleteRecipe = async (id: number) => {
    try {
      const token = localStorage.getItem('authToken');
      const response = await axios.delete(`${baseUrl}/api/users/${user.id}/recipes/${id}`,{
         headers: {
          'Authorization': `Bearer ${token}`,
        },
      })
      setRecipes(prev => prev.filter(recipe => recipe.id !== id));
    } catch (error) {
      console.error('Failed to delete recipe:', error);
      throw error;
    }
  };

  useEffect(() => {
    if (user) {
      fetchRecipes();
    } else {
      setRecipes([]);
    }
  }, [user]);

  return (
    <RecipeContext.Provider value={{
      recipes,
      isLoading,
      searchTerm,
      setSearchTerm,
      filteredRecipes,
      addRecipe,
      deleteRecipe,
      refreshRecipes: fetchRecipes,
    }}>
      {children}
    </RecipeContext.Provider>
  );
}

export function useRecipes() {
  const context = useContext(RecipeContext);
  if (context === undefined) {
    throw new Error('useRecipes must be used within a RecipeProvider');
  }
  return context;
}
