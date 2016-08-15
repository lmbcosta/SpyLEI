package controller;


public abstract class NotAuthenticatedUseCaseController extends UseCaseController {

	@Override
	public boolean preConditionVerified() {
		return true;
	}

	@Override
	public void preConditionError() {
		// nothing to show, since the use case can always be executed.
	}
}
