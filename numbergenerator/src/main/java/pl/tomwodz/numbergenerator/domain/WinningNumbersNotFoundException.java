package pl.tomwodz.numbergenerator.domain;

public class WinningNumbersNotFoundException extends RuntimeException{
    public WinningNumbersNotFoundException(String message) {
        super(message);
    }
}
