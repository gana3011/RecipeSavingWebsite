import React, { useEffect, useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { Button } from '@/components/ui/button';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { InputOTP, InputOTPGroup, InputOTPSlot } from '@/components/ui/input-otp';
import { useToast } from '@/hooks/use-toast';
import axios from 'axios';

const baseUrl = import.meta.env.VITE_API_BASE_URL;

const VerifyOTP = () => {
  const [otp, setOtp] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const { toast } = useToast();
  const navigate = useNavigate();
  const location = useLocation();
  
  const email = location.state?.email || '';

  useEffect(()=>{
    console.log(otp);
  },[]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    if (otp.length !== 6) {
      toast({
        title: "Invalid OTP",
        description: "Please enter a valid 6-digit code.",
        variant: "destructive",
      });
      return;
    }

    setIsLoading(true);

    try {
      await axios.post(`${baseUrl}/api/auth/verify`,
        {email:email,otp:otp.trim()});
      toast({
        title: "Email verified!",
        description: "Your account has been successfully verified.",
      });
      navigate('/dashboard');
    } catch (error) {
      toast({
        title: "Verification failed",
        description: "The OTP you entered is incorrect. Please try again.",
        variant: "destructive",
      });
    } finally {
      setIsLoading(false);
    }
  };

  const handleResendOTP = async () => {
    try {
      await axios.post(`${baseUrl}/api/auth/resend`, {email: email.trim()});
      toast({
        title: "OTP sent!",
        description: "A new verification code has been sent to your email.",
      });
    } catch (error) {
      toast({
        title: "Failed to resend",
        description: "Could not resend the verification code. Please try again.",
        variant: "destructive",
      });
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-recipe-cream via-white to-recipe-cream-dark p-4">
      <div className="w-full max-w-md animate-fade-in">
        <Card className="shadow-xl border-0">
          <CardHeader className="text-center bg-gradient-to-r from-recipe-orange to-recipe-orange-light text-white rounded-t-lg">
            <CardTitle className="text-2xl font-bold">Verify Your Email</CardTitle>
            <CardDescription className="text-orange-100">
              Enter the 6-digit code sent to your email
            </CardDescription>
          </CardHeader>
          <CardContent className="p-6 bg-white space-y-6">
            <div className="text-center">
              <p className="text-sm text-gray-600 mb-2">
                We've sent a verification code to:
              </p>
              <p className="font-medium text-gray-800">{email}</p>
            </div>

            <form onSubmit={handleSubmit} className="space-y-6">
              <div className="space-y-2">
                <Label htmlFor="otp" className="text-center block">
                  Enter verification code
                </Label>
                <div className="flex justify-center">
                  <InputOTP
                    value={otp}
                    onChange={setOtp}
                    maxLength={6}
                  >
                    <InputOTPGroup>
                      <InputOTPSlot index={0} />
                      <InputOTPSlot index={1} />
                      <InputOTPSlot index={2} />
                      <InputOTPSlot index={3} />
                      <InputOTPSlot index={4} />
                      <InputOTPSlot index={5} />
                    </InputOTPGroup>
                  </InputOTP>
                </div>
              </div>

              <Button
                type="submit"
                className="w-full bg-recipe-orange hover:bg-recipe-orange-light transition-all duration-200 transform hover:scale-105"
                disabled={isLoading || otp.length !== 6}
              >
                {isLoading ? 'Verifying...' : 'Verify Email'}
              </Button>
            </form>

            <div className="text-center space-y-2">
              <p className="text-sm text-gray-600">
                Didn't receive the code?
              </p>
              <Button
                variant="ghost"
                onClick={handleResendOTP}
                className="text-recipe-orange hover:text-recipe-orange-light p-0 h-auto font-medium"
              >
                Resend verification code
              </Button>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
};

export default VerifyOTP;