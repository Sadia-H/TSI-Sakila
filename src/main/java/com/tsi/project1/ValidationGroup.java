package com.tsi.project1;

import lombok.Builder;
import jakarta.validation.groups.Default;

public final class ValidationGroup {
    public interface Create extends Default {}
    public interface Update extends Default {}
}
