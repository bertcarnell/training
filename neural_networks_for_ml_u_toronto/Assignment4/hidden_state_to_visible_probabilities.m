function visible_probability = hidden_state_to_visible_probabilities(rbm_w, hidden_state)
% <rbm_w> is a matrix of size <number of hidden units> by <number of visible units>
% <hidden_state> is a binary matrix of size <number of hidden units> by <number of configurations that we're handling in parallel>.
% The returned value is a matrix of size <number of visible units> by <number of configurations that we're handling in parallel>.
% This takes in the (binary) states of the hidden units, and returns the activation probabilities of the visible units, conditional on those states.
%m = size(rbm_w, 1);
%N = size(rbm_w, 2);
%c = size(hidden_state, 2);
visible_probability = 1.0 ./ (1.0 + exp(-1.0 .* transpose(rbm_w) * hidden_state));
%    error('not yet implemented');
end
