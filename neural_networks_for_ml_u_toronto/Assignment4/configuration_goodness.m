function G = configuration_goodness(rbm_w, visible_state, hidden_state)
% <rbm_w> is a matrix of size <number of hidden units> by <number of visible units>
% <visible_state> is a binary matrix of size <number of visible units> by <number of configurations that we're handling in parallel>.
% <hidden_state> is a binary matrix of size <number of hidden units> by <number of configurations that we're handling in parallel>.
% This returns a scalar: the mean over cases of the goodness (negative energy) of the described configurations.

% unsure which is faster, the for loop or grabbing the diagonal

%c = size(visible_state, 2);
%tempG = zeros(c);
% 1 x m * m x N * N x 1
%for i = 1:c
%  tempg(i) = transpose(hidden_state(:,i)) * rbm_w * visible_state(:,i)
%endfor
%G = mean(tempg);

% c x m * m x N * N x c
G = mean(diag(transpose(hidden_state) * rbm_w * visible_state));

%    error('not yet implemented');
end
