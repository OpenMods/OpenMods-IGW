/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Open Mods
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package openmods.igw.api.integration;

import javax.annotation.Nonnull;

/**
 * Wraps every exception thrown during integration loading.
 *
 * @since 1.0
 */
public class IntegrationFailedException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of {@code cause.toString()} (which typically contains
     * the class and detail message of {@code cause}).
     *
     * @param cause
     *         The cause (which is saved for later retrieval by the
     *         {@link #getCause()} method). It must not be {@code null}.
     *
     * @since 1.0
     */
    public IntegrationFailedException(@Nonnull final Throwable cause) {
        super(cause);
    }
}
