
import React from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Badge } from '@/components/ui/badge';
import { Recipe } from '@/contexts/RecipeContext';
import { useRecipes } from '@/contexts/RecipeContext';
import { ExternalLink, X } from 'lucide-react';
import { useToast } from '@/hooks/use-toast';

interface RecipeCardProps {
  recipe: Recipe;
}

const RecipeCard: React.FC<RecipeCardProps> = ({ recipe }) => {
  const { deleteRecipe } = useRecipes();
  const { toast } = useToast();

  const handleDelete = async () => {
    try {
      await deleteRecipe(recipe.id);
      toast({
        title: "Recipe deleted",
        description: `"${recipe.name}" has been removed from your collection.`,
      });
    } catch (error) {
      toast({
        title: "Error",
        description: "Failed to delete recipe. Please try again.",
        variant: "destructive",
      });
    }
  };

  const handleVisitUrl = () => {
    if (recipe.url) {
      window.open(recipe.url, '_blank', 'noopener,noreferrer');
    }
  };

  return (
    <Card className="group hover:shadow-lg transition-all duration-300 border-0 shadow-sm bg-white hover:transform hover:scale-105">
      <CardHeader className="pb-3">
        <div className="flex justify-between items-start">
          <CardTitle className="text-lg font-semibold text-gray-900 line-clamp-2 group-hover:text-recipe-orange transition-colors">
            {recipe.name}
          </CardTitle>
          <Button
            variant="ghost"
            size="sm"
            onClick={handleDelete}
            className="opacity-0 group-hover:opacity-100 transition-opacity text-gray-400 hover:text-red-500 p-1 h-auto"
          >
            <X className="w-4 h-4" />
          </Button>
        </div>
      </CardHeader>
      
      <CardContent className="space-y-4">
        <p className="text-gray-600 text-sm line-clamp-3">
          {recipe.description}
        </p>
        
        {recipe.tags.length > 0 && (
          <div className="flex flex-wrap gap-1">
            {recipe.tags.map((tag, index) => (
              <Badge 
                key={index} 
                variant="secondary" 
                className="text-xs bg-recipe-cream text-recipe-brown hover:bg-recipe-cream-dark transition-colors"
              >
                {tag}
              </Badge>
            ))}
          </div>
        )}
        
        <div className="flex justify-between items-center pt-2">
          <span className="text-xs text-gray-500">
            Added {new Date(recipe.createdAt).toLocaleDateString()}
          </span>
          
          {recipe.url && (
            <Button
              variant="outline"
              size="sm"
              onClick={handleVisitUrl}
              className="flex items-center space-x-1 text-recipe-orange border-recipe-orange hover:bg-recipe-orange hover:text-white transition-all"
            >
              <ExternalLink className="w-3 h-3" />
              <span className="text-xs">Visit</span>
            </Button>
          )}
        </div>
      </CardContent>
    </Card>
  );
};

export default RecipeCard;
