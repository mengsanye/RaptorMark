#ifndef RAPTORMARK_HELPER_H
#define RAPTORMARK_HELPER_H

#ifdef __cplusplus
extern "C" {
#endif

#define DEFAULT_KERNEL_VERSION_MAJOR 3
#define DEFAULT_KERNEL_VERSION_MINOR 10

#define IO_URING_KERNEL_VERSION_MAJOR 5
#define IO_URING_KERNEL_VERSION_MINOR 4

#define ARGV_OPTION_MAX_LENGTH 256

typedef struct {
    const char *key;
    int val;
} strPairStruct;

enum {
    ENGINE_MMAP,
    ENGINE_PSYNC,
    ENGINE_IO_URING,
    ENGINE_MAX
};

static strPairStruct engineLut[] = {
        {"mmap",     ENGINE_MMAP},
        {"psync",    ENGINE_PSYNC},
        {"io_uring", ENGINE_IO_URING},
};

bool checkEngineAvailability(char *engine);
void json2Options(const char *jsonStr, int *argc, char ***argv);
void freeOptions(int *argc, char ***argv);

#ifdef __cplusplus
}
#endif

#endif //RAPTORMARK_HELPER_H
