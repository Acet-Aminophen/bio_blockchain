package bioBlockchain.exception;

public class UnvalidHashAnswerException extends RuntimeException
{
    public UnvalidHashAnswerException() {
        super("Your bioBlockchain.Hash Answer is not correct.");
    }
}
