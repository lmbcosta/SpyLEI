package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import domain.interfaces.IOrganization;
import view.MainView;

public abstract class UseCaseController {

	MainView mainView;
	IOrganization app;

	private int stateToChangeTo;
	private int currentState;
	private Map<Integer, List<StateTransition>> states;
	private boolean executingUseCase;

	static final int ANY_STATE = 0;
	static final int BEGIN = 1;

	public UseCaseController() {
		states = new HashMap<>();
	}

	public static abstract class StateTransition {
		private int fromState;
		private int toState;
		boolean succeeded;

		public StateTransition(int fromState, int toState) {
			this.fromState = fromState;
			this.toState = toState;
			succeeded = true;
		}

		public boolean canChangeToNextState() {
			return true;
		}

		// @pre: canChangeToNextState();
		public abstract void changeToNextState();

		// @pre: changeSucceeded() && !isFinalState()
		public int getNextState() {
			return toState;
		}

		public int getFromState() {
			return fromState;
		}

		public boolean changeSucceeded() {
			return succeeded;
		}

		public void emitError() {
		}
	}

	public void addState(StateTransition stateTransition) {
		if (!states.containsKey(stateTransition.getFromState()))
			states.put(stateTransition.getFromState(), new LinkedList<>());
		states.get(stateTransition.getFromState()).add(stateTransition);
	}

	/**
	 * Is called by the main view to give a reference to itself and to the
	 * domain root object.
	 * 
	 */
	public void setViewAndDomain(MainView view, IOrganization app) {
		this.mainView = view;
		this.app = app;
	}

	public void init() {
		currentState = BEGIN;
	}

	public abstract boolean preConditionVerified();

	public abstract void preConditionError();

	public boolean executingUseCase() {
		return executingUseCase;
	}

	boolean executeUseCase(int stateToChangeTo) {
		executingUseCase = true;
		this.stateToChangeTo = stateToChangeTo;
		int state = currentState;
		boolean error = false, stop = false;
		do {
			StateTransition stateTransition = getStateTransition(state);
			if (stateTransition == null)
				stop = true;
			else {
				if (stateTransition.canChangeToNextState()) {
					stateTransition.changeToNextState();
					if (stateTransition.changeSucceeded())
						state = stateTransition.getNextState();
					else
						error = true;
				} else {
					stateTransition.emitError();
					error = true;
				}
			}
		} while (state != stateToChangeTo && !stop && !error);

		if (!stop && !error)
			currentState = state;
		executingUseCase = false;
		return !error && !stop;
	}

	public int currentState() {
		return currentState;
	}

	public int stateToChangeTo() {
		return stateToChangeTo;
	}

	private StateTransition getStateTransition(int state) {
		// try wildcard transition
		List<StateTransition> transitions = states.get(ANY_STATE);
		if (transitions != null)
			for (StateTransition transition : transitions)
				if (transition.canChangeToNextState())
					return transition;
		// try specific transition
		transitions = states.get(state);
		if (transitions != null) {
			for (StateTransition transition : transitions)
				if (transition.canChangeToNextState())
					return transition;
			if (transitions.size() > 0) // if there is at least one transition,
										// but
				return transitions.get(0); // none satisfies the condition,
											// returns the
		} // first, so an error can be given
			// if not found, bad luck!
		return null;
	}

	/**
	 * Update the controller (called after popAndUpdate)
	 */
	public void update() {
	}
}