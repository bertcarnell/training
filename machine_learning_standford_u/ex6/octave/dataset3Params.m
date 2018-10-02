function [C, sigma] = dataset3Params(X, y, Xval, yval)
%DATASET3PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = DATASET3PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 1;
sigma = 0.3;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%

gridSearch = 0;
if (gridSearch == 1)
  Csearch = [0.01, 0.03, 0.1, 0.3, 1.0, 3.0, 10.0, 30.0];
  sigmaSearch = [0.01, 0.03, 0.1, 0.3, 1.0, 3.0, 10.0, 30.0];

  Csearch_len = length(Csearch);
  sigmaSearch_len = length(sigmaSearch);

  val = zeros(Csearch_len, sigmaSearch_len);
  minVal = 1E6;
  cmin = 0;
  smin = 0;
  for i=1:Csearch_len
    for j=1:sigmaSearch_len
      model= svmTrain(X, y, Csearch(i), @(x1, x2) gaussianKernel(x1, x2, sigmaSearch(j))); 
      predictions = svmPredict(model, Xval);
      val(i,j) = mean(double(predictions ~= yval));
      if (val(i,j) < minVal)
        minVal = val(i,j);
        cmin = i;
        smin = j;
      endif
    endfor
  endfor

  C = Csearch(cmin);
  sigma = sigmaSearch(smin);
  val
  C
  sigma
endif

% make submissions faster
if (gridSearch == 0)
  C = 1;
  sigma = 0.1;
endif

% =========================================================================

end
