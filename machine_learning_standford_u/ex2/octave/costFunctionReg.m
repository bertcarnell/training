function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta

temp = X * theta;
htheta = sigmoid(temp);
log_htheta = log(htheta);
log_1_m_htheta = log(1 - htheta);
n = size(X,2)

for i = 1:m
  J = J + 1 / m * ( -1*y(i)*log_htheta(i) - (1-y(i))*log_1_m_htheta(i));
end
for j = 2:n
  J = J + lambda / 2 / m * theta(j)*theta(j);
end

grad = 1 / m * X' * (htheta - y);

for j = 2:n
  grad(j) = grad(j) + lambda / m * theta(j);
end




% =============================================================

end
