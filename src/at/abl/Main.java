package at.abl;

import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        MyClassLoader myClassLoaderParent = new MyClassLoader(ClassLoader.getSystemClassLoader(), false, true);
        MyClassLoader myClassLoader1 = new MyClassLoader(myClassLoaderParent, true, false);
        MyClassLoader myClassLoader2 = new MyClassLoader(myClassLoaderParent, true, false);

        Class<?> myClass1 = Class.forName("at.abl.MyClass", false, myClassLoader1);
        Class<?> myClass2 = Class.forName("at.abl.MyClass", false, myClassLoader2);
        Class<?> introspectionClass1 = Class.forName("at.abl.Introspection", false, myClassLoader1);
        Class<?> introspectionClass2 = Class.forName("at.abl.Introspection", false, myClassLoader2);

        System.out.println(myClass1 + " " + myClass1.hashCode());
        System.out.println(myClass2 + " " + myClass2.hashCode());
        System.out.println(introspectionClass1 + " " + introspectionClass1.hashCode());
        System.out.println(introspectionClass2 + " " + introspectionClass2.hashCode());

        myClass1.getMethod("doIt").invoke(null);
        myClass2.getMethod("doIt").invoke(null);
    }

    public static class MyClassLoader extends ClassLoader {

        private final boolean loadMyClass;
        private final boolean loadIntrospection;

        public MyClassLoader(ClassLoader parent, boolean loadMyClass, boolean loadIntrospection) {
            super(parent);
            this.loadMyClass = loadMyClass;
            this.loadIntrospection = loadIntrospection;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if (name.equals("at.abl.MyClass") && loadMyClass) {
                byte[] bytes = Base64.getDecoder().decode("yv66vgAAAD0AOgoAAgADBwAEDAAFAAYBABBqYXZhL2xhbmcvT2JqZWN0AQAGPGluaXQ+AQADKClWCgAIAAkHAAoMAAsABgEAFGF0L2FibC9JbnRyb3NwZWN0aW9uAQAEY2FsbAkADQAOBwAPDAAQABEBAA5hdC9hYmwvTXlDbGFzcwEAB2NvdW50ZXIBAAFJCQATABQHABUMABYAFwEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsSAAAAGQwAGgAbAQAXbWFrZUNvbmNhdFdpdGhDb25zdGFudHMBABUoSSlMamF2YS9sYW5nL1N0cmluZzsKAB0AHgcAHwwAIAAhAQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEAEExhdC9hYmwvTXlDbGFzczsBAARkb0l0AQAIPGNsaW5pdD4BAApTb3VyY2VGaWxlAQAMTXlDbGFzcy5qYXZhAQAQQm9vdHN0cmFwTWV0aG9kcw8GAC0KAC4ALwcAMAwAGgAxAQAkamF2YS9sYW5nL2ludm9rZS9TdHJpbmdDb25jYXRGYWN0b3J5AQCYKExqYXZhL2xhbmcvaW52b2tlL01ldGhvZEhhbmRsZXMkTG9va3VwO0xqYXZhL2xhbmcvU3RyaW5nO0xqYXZhL2xhbmcvaW52b2tlL01ldGhvZFR5cGU7TGphdmEvbGFuZy9TdHJpbmc7W0xqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9sYW5nL2ludm9rZS9DYWxsU2l0ZTsIADMBABhkb2luZyBpdCEgbmV3IGNvdW50ZXI6IAEBAAxJbm5lckNsYXNzZXMHADYBACVqYXZhL2xhbmcvaW52b2tlL01ldGhvZEhhbmRsZXMkTG9va3VwBwA4AQAeamF2YS9sYW5nL2ludm9rZS9NZXRob2RIYW5kbGVzAQAGTG9va3VwACEADQACAAAAAQAKABAAEQAAAAMAAQAFAAYAAQAiAAAALwABAAEAAAAFKrcAAbEAAAACACMAAAAGAAEAAAADACQAAAAMAAEAAAAFACUAJgAAAAkAJwAGAAEAIgAAAD4AAgAAAAAAGrgAB7IADARgswAMsgASsgAMugAYAAC2AByxAAAAAQAjAAAAEgAEAAAABwADAAgACwAJABkACgAIACgABgABACIAAAAdAAEAAAAAAAUDswAMsQAAAAEAIwAAAAYAAQAAAAQAAwApAAAAAgAqACsAAAAIAAEALAABADIANAAAAAoAAQA1ADcAOQAZ");
                return defineClass("at.abl.MyClass", bytes, 0, bytes.length);
            }
            if (name.equals("at.abl.Introspection") && loadIntrospection) {
                byte[] bytes = Base64.getDecoder().decode("yv66vgAAAD0ANQoAAgADBwAEDAAFAAYBABBqYXZhL2xhbmcvT2JqZWN0AQAGPGluaXQ+AQADKClWCQAIAAkHAAoMAAsADAEAFGF0L2FibC9JbnRyb3NwZWN0aW9uAQAHY291bnRlcgEAAUkJAA4ADwcAEAwAEQASAQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOxIAAAAUDAAVABYBABdtYWtlQ29uY2F0V2l0aENvbnN0YW50cwEAFShJKUxqYXZhL2xhbmcvU3RyaW5nOwoAGAAZBwAaDAAbABwBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQAWTGF0L2FibC9JbnRyb3NwZWN0aW9uOwEABGNhbGwBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBABJJbnRyb3NwZWN0aW9uLmphdmEBABBCb290c3RyYXBNZXRob2RzDwYAKAoAKQAqBwArDAAVACwBACRqYXZhL2xhbmcvaW52b2tlL1N0cmluZ0NvbmNhdEZhY3RvcnkBAJgoTGphdmEvbGFuZy9pbnZva2UvTWV0aG9kSGFuZGxlcyRMb29rdXA7TGphdmEvbGFuZy9TdHJpbmc7TGphdmEvbGFuZy9pbnZva2UvTWV0aG9kVHlwZTtMamF2YS9sYW5nL1N0cmluZztbTGphdmEvbGFuZy9PYmplY3Q7KUxqYXZhL2xhbmcvaW52b2tlL0NhbGxTaXRlOwgALgEAHWludHJvc3BlY3Rpb24hIG5ldyBjb3VudGVyOiABAQAMSW5uZXJDbGFzc2VzBwAxAQAlamF2YS9sYW5nL2ludm9rZS9NZXRob2RIYW5kbGVzJExvb2t1cAcAMwEAHmphdmEvbGFuZy9pbnZva2UvTWV0aG9kSGFuZGxlcwEABkxvb2t1cAAhAAgAAgAAAAEACgALAAwAAAADAAEABQAGAAEAHQAAAC8AAQABAAAABSq3AAGxAAAAAgAeAAAABgABAAAAAwAfAAAADAABAAAABQAgACEAAAAJACIABgABAB0AAAA3AAIAAAAAABeyAAcEYLMAB7IADbIAB7oAEwAAtgAXsQAAAAEAHgAAAA4AAwAAAAgACAAJABYACgAIACMABgABAB0AAAAdAAEAAAAAAAUDswAHsQAAAAEAHgAAAAYAAQAAAAUAAwAkAAAAAgAlACYAAAAIAAEAJwABAC0ALwAAAAoAAQAwADIANAAZ");
                return defineClass("at.abl.Introspection", bytes, 0, bytes.length);
            }
            throw new ClassNotFoundException("not found in myclassloader");
        }
    }
}
