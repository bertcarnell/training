function ret = cd1(rbm_w, visible_data)
% <rbm_w> is a matrix of size <number of hidden units> by <number of visible units>
% <visible_data> is a (possibly but not necessarily binary) matrix of size <number of visible units> by <number of data cases>
% The returned value is the gradient approximation produced by CD-1. It's of the same shape as <rbm_w>.
visible_data = sample_bernoulli(visible_data);

% we'll sample a binary state for the hidden units conditional on the data; 
% m x c
hidden_probs = visible_state_to_hidden_probabilities(rbm_w, visible_data);
% m x c
hidden_state = sample_bernoulli(hidden_probs);

% we'll sample a binary state for the visible units conditional on that binary hidden 
% state (this is sometimes called the "reconstruction" for the visible units); 
% N x c
visible_probs = hidden_state_to_visible_probabilities(rbm_w, hidden_state);
% N x c
visible_state = sample_bernoulli(visible_probs);

% and we'll sample a binary state for the hidden units conditional on that binary 
% visible "reconstruction" state. 
% m x c
hidden_probs2 = visible_state_to_hidden_probabilities(rbm_w, visible_state);
% m x c
% hidden_state2 = sample_bernoulli(hidden_probs2);

% Then we base our gradient estimate on all those sampled binary states. 
% config_good1 = configuration_goodness(rbm_w, visible_data, hidden_state);
% config_good2 = configuration_goodness(rbm_w, visible_state, hidden_state2);
config_good_grad1 = configuration_goodness_gradient(visible_data, hidden_state);
% config_good_grad2 = configuration_goodness_gradient(visible_state, hidden_state2);
config_good_grad2 = configuration_goodness_gradient(visible_state, hidden_probs2);

% The configuration goodness gradient function will be useful twice, for CD-1:
% We use it once on the given data and the hidden state that it gives rise to. 
% That gives us the direction of changing the weights that will make the data have 
% greater goodness, which is what we want to achieve.

% We also use it on the "reconstruction" visible state and the hidden state 
% that it gives rise to. That gives us the direction of changing the weights 
% that will make the reconstruction have greater goodness, so we want to go in 
% the opposite direction, because we want to make the reconstruction have less goodness.

ret = config_good_grad1 - config_good_grad2;

%    error('not yet implemented');
end
