
import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '@/contexts/AuthContext';
import { Button } from '@/components/ui/button';

const Index = () => {
  const { user, isLoading } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!isLoading && user) {
      navigate('/dashboard');
    }
  }, [user, isLoading, navigate]);

  if (isLoading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-recipe-cream via-white to-recipe-cream-dark">
        <div className="text-center">
          <div className="w-16 h-16 mx-auto mb-4 bg-gradient-to-r from-recipe-orange to-recipe-orange-light rounded-full flex items-center justify-center">
            <div className="w-8 h-8 border-4 border-white border-t-transparent rounded-full animate-spin"></div>
          </div>
          <p className="text-gray-600">Loading...</p>
        </div>
      </div>
    );
  }

  if (user) {
    return null;
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-recipe-cream via-white to-recipe-cream-dark">
      <div className="flex flex-col items-center justify-center min-h-screen px-4">
        <div className="text-center max-w-4xl mx-auto animate-fade-in">
          {/* Logo and Brand */}
          <div className="mb-8">
            {/* <div className="w-20 h-20 mx-auto mb-6 bg-gradient-to-r from-recipe-orange to-recipe-orange-light rounded-2xl flex items-center justify-center shadow-lg">
              <span className="text-white font-bold text-2xl">RK</span>
            </div> */}
            <h1 className="text-5xl font-bold text-gray-900 mb-4">
              Flavour File
            </h1>
            <p className="text-xl text-gray-600 mb-8">
              Your personal culinary collection, organized and accessible anywhere
            </p>
          </div>

          {/* Features */}
          <div className="grid md:grid-cols-3 gap-8 mb-12">
            <div className="text-center p-6 bg-white rounded-xl shadow-sm border border-gray-100 hover:shadow-md transition-shadow">
              <div className="w-12 h-12 mx-auto mb-4 bg-recipe-cream rounded-lg flex items-center justify-center">
                <span className="text-recipe-orange font-bold text-lg">📖</span>
              </div>
              <h3 className="font-semibold text-gray-900 mb-2">Save & Organize</h3>
              <p className="text-gray-600 text-sm">Store all your favorite recipes with descriptions, URLs, and custom tags</p>
            </div>
            
            <div className="text-center p-6 bg-white rounded-xl shadow-sm border border-gray-100 hover:shadow-md transition-shadow">
              <div className="w-12 h-12 mx-auto mb-4 bg-recipe-cream rounded-lg flex items-center justify-center">
                <span className="text-recipe-orange font-bold text-lg">🔍</span>
              </div>
              <h3 className="font-semibold text-gray-900 mb-2">Quick Search</h3>
              <p className="text-gray-600 text-sm">Find recipes instantly by name, description, or tags</p>
            </div>
            
            <div className="text-center p-6 bg-white rounded-xl shadow-sm border border-gray-100 hover:shadow-md transition-shadow">
              <div className="w-12 h-12 mx-auto mb-4 bg-recipe-cream rounded-lg flex items-center justify-center">
                <span className="text-recipe-orange font-bold text-lg">🌐</span>
              </div>
              <h3 className="font-semibold text-gray-900 mb-2">Access Anywhere</h3>
              <p className="text-gray-600 text-sm">Your recipes are always available, whether you're at home or shopping</p>
            </div>
          </div>

          {/* CTA Buttons */}
          <div className="flex flex-col sm:flex-row gap-4 justify-center items-center">
            <Button
              onClick={() => navigate('/signup')}
              size="lg"
              className="bg-recipe-orange hover:bg-recipe-orange-light text-white px-8 py-3 text-lg font-medium transition-all duration-200 transform hover:scale-105 shadow-lg"
            >
              Start Collecting Recipes
            </Button>
            <Button
              onClick={() => navigate('/signin')}
              variant="outline"
              size="lg"
              className="border-recipe-orange text-recipe-orange hover:bg-recipe-orange hover:text-white px-8 py-3 text-lg font-medium transition-all duration-200"
            >
              Sign In
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Index;
