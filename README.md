

## 2. Cómo escribir (Sintaxis de Markdown)

Markdown es muy sencillo. Aquí tienes los comandos que más usarás para tu documento:

* **Títulos:** Se usa el '#'. Entre más `#`, más pequeño el título (`#` Grande, '##' Mediano, '###' Pequeño).
* **Negritas:** Rodea el texto con doble asterisco: '**texto en negrita**'.
* **Código:** Para una línea usa \'código\'. Para un bloque de varias líneas usa tres tildes invertidas: \' \' \'bash (o java).
* **Listas:** Usa un asterisco `*` o un guion `-` al inicio de la línea.


## 3. Buenas Prácticas de Documentación

Para que tu documentación sea útil de verdad, sigue estos consejos:

1.  **Mantenla Actualizada:** Si añades una nueva dependencia en el `pom.xml` o cambias un endpoint en tu Controller, actualiza el `README.md` en el mismo Pull Request.
2.  **Documenta los Errores Comunes:** Si tuviste un problema configurando el puerto o la base de datos, escribe la solución en una sección de "Solución de Problemas".
3.  **Capturas de Pantalla:** Si tu aplicación tiene una interfaz o usas Postman para probarla, sube una imagen a la carpeta `assets` de tu repo e inclúyela: `![Descripción](ruta/a/la/imagen.png)`.

---

## 4. El Flujo de Trabajo (Git + Docs)

Cada vez que quieras escribir o mejorar la documentación, sigue el flujo de **Pull Request** que aprendimos:

1.  **Crea una rama:** `git checkout -b docs/update-readme`
2.  **Edita el archivo:** Abre el `README.md` en VS Code o IntelliJ y escribe.
3.  **Guarda y sube:**
    ```bash
    git add README.md
    git commit -m "docs: actualizar instrucciones de instalación"
    git push origin docs/update-readme
    ```
4.  **Fusiona:** Abre el PR en GitHub y únelo a `main`.

