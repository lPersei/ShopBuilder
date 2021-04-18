package com.persei;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {
    private static Shop shop;
    private static long window;
    private static Model model;
    private static String ansver;
    public static Thread th;

    public static void main(String[] args) {

        ShopBuilder shopBuilder = new ShopBuilder();
        ShopBuilderDirector shopBuilderDirector = new ShopBuilderDirector();
        shop = shopBuilderDirector.constructShop(shopBuilder);
        model = new Model(shop);

        th = new Thread(runnable);
        th.start();

        while (true){
            System.out.println("Rerun? - y/n ");
            ansver = shopBuilderDirector.scanner.next();
            if (ansver.compareTo("y") == 0){
                model.SetRandomValues();
            } else break;
        }
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
        System.exit(-1);

    }

    static Runnable runnable = new Runnable() {
        public void run() {
            // Setup an error callback. The default implementation
            // will print the error message in System.err.
            GLFWErrorCallback.createPrint(System.err).set();

            // Initialize GLFW. Most GLFW functions will not work before doing this.
            if (!glfwInit())
                throw new IllegalStateException("Unable to initialize GLFW");

            // Configure GLFW
            glfwDefaultWindowHints(); // optional, the current window hints are already the default
            glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable

            // Create the window
            window = glfwCreateWindow(800, 800, "Hello World!", NULL, NULL);
            if (window == NULL)
                throw new RuntimeException("Failed to create the GLFW window");

            // Make the OpenGL context current
            glfwMakeContextCurrent(window);

            // This line is critical for LWJGL's interoperation with GLFW's
            // OpenGL context, or any context that is managed externally.
            // LWJGL detects the context that is current in the current thread,
            // creates the GLCapabilities instance and makes the OpenGL
            // bindings available for use.
            GL.createCapabilities();

            // Set the clear color
            glClearColor(0.3f, 0.1f, 0.3f, 0.1f);

            // Run the rendering loop until the user has attempted to close
            // the window or has pressed the ESCAPE key.
            while (!glfwWindowShouldClose(window)) {

                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

                glViewport(-200, -200, 800, 800);

                model.DrawWalls();
                model.DrawEntrance();
                model.DrawCounter();
                model.DrawShelves();


                glfwSwapBuffers(window); // swap the color buffers

                // Poll for window events. The key callback above will only be
                // invoked during this call.
                glfwPollEvents();

            }

            // Free the window callbacks and destroy the window
            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);

            // Terminate GLFW and free the error callback
            glfwTerminate();
            glfwSetErrorCallback(null).free();
            System.exit(-1);
        }
    };
}
